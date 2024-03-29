# Amazon EC2 (Elastic Compute Cloud) <img align="right" width="8%" alt="image" src="https://user-images.githubusercontent.com/48696735/180069707-f7b3f370-0228-4f6e-8e14-62af470dac7d.png">

- [Overview](#overview "Overview")
- [EC2 sizing & configuration options](#ec2-sizing--configuration-options "EC2 sizing & configuration options")
- [EC2 User Data Script](#ec2-user-data-script "EC2 User Data Script")
- [EC2 Instance Types](#ec2-instance-types "EC2 Instance Types")
- [EC2 Security Groups](#ec2-security-groups "EC2 Security Groups")
- [Classic Ports](#classic-ports "Classic Ports")
- [How to SSH into EC2 instance](#how-to-ssh-into-ec2-instance "How to SSH into EC2 instance")
- [Private vs Public vs Elastic IP](#private-vs-public-vs-elastic-ip "Private vs Public vs Elastic IP")
- [EC2 Placement Groups](#ec2-placement-groups "EC2 Placement Groups")
  - Cluster
  - Spread
  - Partition
- [Elastic Network Interfaces (ENI)](#elastic-network-interfaces-eni "Elastic Network Interfaces (ENI)")
- [EC2 Hibernate](#ec2-hibernate "EC2 Hibernate")
- [EC2 Nitro](#ec2-nitro "EC2 Nitro")
- [Understanding vCPU](#understanding-vcpu "Understanding vCPU")
- [Capacity Reservations](#capacity-reservations "Capacity Reservations")

## Overview

- EC2 is one of the most popular AWS offering
- EC2 is IaaS

## EC2 sizing & configuration options

- **OS:** Linux, Windows or Mac OS
- **CPU**
- **RAM**
- **Storage space**
  - Network-attached (EBS & EFS)
  - Hardware (EC2 Instance Store)
- **Network Card:** speed of the card, Public IP address
- **Firewall rules:** security group
- **Bootstrap script (configure at first launch):** EC2 User Data

## EC2 User Data Script

- It is possible to bootstrap our instances using an **EC2 User Data** script
- **bootstrapping** means executing commands when a machine starts
- This script is **run only once** when the instance is launched for the first time
- EC2 user data is used to automate boot tasks such as:
  - Installing updates
  - Installing software
  - Downloading common files from the Internet
  - Anything you can thing of
- This script runs with the **root user** 

## EC2 Instance Types

There are many instance types, below are just few examples:

<img width="60%" alt="image" src="https://user-images.githubusercontent.com/48696735/180078336-2377c40f-d3cb-49b8-bf75-5c3cbd9d6e7c.png">

**t2.micro is part of the AWS free tier** (up to 750 hours per month)

### Naming convention

`m5.2xlarge`

- **m**: instance class
- **5**: generation (AWS improves them over time)
- **2xlarge**: size within the instance class

## EC2 Security Groups

- Security Groups are the fundamental of **network security** in AWS
- They control how traffic is allowed into or out our EC2 instances

  <img width="50%" alt="image" src="https://user-images.githubusercontent.com/48696735/180201245-dcc319ab-5657-4add-82a8-5452775f2200.png">

- Security groups only contain *allow* rules
- Security group can have rules that can be referenced by IP or by other security group

- Security groups act as a **"firewall"** on EC2 instances
- They regulate:
  - Access to Ports
  - Authorised IP ranges - IPv4 and IPv6
  - Control of inbound network (from outside to the instance)
  - Control of outbound network (from instance to the outside)
    <img width="1768" alt="image" src="https://user-images.githubusercontent.com/48696735/180202139-586fe3ad-8845-46d8-8eaa-473c977df689.png">

- Security groups can be attached to multiple instances
- They are locked down to a region/VPC combination
- SGs live "outside" the EC2 - if traffic is blocked the EC2 instance won't see it
- *It is good to maintain one separate SG for SSH access*
- If your application is not accessible (**time out**), then it is probably a SG issue
- If your application gives a "**connection refused**" error, then it is an application error or it is not launched
- All **inbound** traffic is blocked ❌ by default
- All **outbound** traffic is allowed ✅ by default

### Referencing other Security Groups
  
<img width="80%" alt="image" src="https://user-images.githubusercontent.com/48696735/180203602-2da6b756-b84a-4766-82d6-bc7670bc3ba1.png">
  
## Classic Ports
  
- **22**   : SSH (Secure Shell) - log into a Linux instance
- **21**   : FTP (File Transfer Protocol) - upload files into a file share
- **22**   : SFTP (Secure FTP) - upload files using SSH
- **80**   : HTTP - access unsecured websites
- **443**  : HTTPS - access secured websites
- **3389** : RDP (Remote Desktop Protocol) - log into a Windows instance

## How to SSH into EC2 instance

Locate your private key file (`.cer` or `.pem` file that you got while creating the EC2 instance)

Run below command to ensure your key is not publicly viewable:

```shell
~ chmod 400 EC2Tutorial.cer
```

Login using user `ec2-user` to the Public IPv4 address of EC2 instance

```shell
~ ssh -i EC2Tutorial.cer ec2-user@65.0.199.110
```

- We can also use **EC2 Instance Connect** to connect to our EC2 instance from browser only
- It does use SSH behind the scenes

## Private vs Public vs Elastic IP

Networking has two types of IPs : **IPv4** and **IPv6**

IPv4 is still the most common format used online
IPv6 is newer and is used in IoT (Internet of Things)

IPv4 allows for **3.7 billion** different addresses in the public space
`[0-255].[0-255].[0-255].[0-255]`

<img width="80%" alt="image" src="https://user-images.githubusercontent.com/48696735/180228200-6338ee56-5bc9-4509-a6d7-cfcb5ee821cf.png">

- **Public IP:**
  - The machine can be identified on the Internet
  - Must be unique across the whole web (two machines cannot have the same public IP)
  - Can be geo-located easily

- **Private IP:**
  - The machine can only be identified on a private network
  - The IP must be unique across the private network
  - But two different private networks (two companies) can have the same IPs
  - Machines connect to WWW using an Internet Gateway (a proxy)
  - Only a specified range of IPs can be used as private IP

- **Elastic IP:**
  - When you stop and then start an EC2 instance, it can change its public IP
  - If you need to have a fixed public IP for your instance, you need an **Elastic IP**
  - An Elastic IP is a public IPv4 IP you own as long as you don't delete it
  - You can attach it to one instance at a time
  - With an Elastic IP address, you can mask the failure of an instance or software by quickly remapping the address to another instance in your account
  - You can only have 5 Elastic IPs in your account (you can ask AWS to increase that)
  - **Try to avoid using Elastic IPs** ❌
    - They often reflect poor architectural decisions
    - Instead, use a random public IP and register a **DNS** name to it
    - Or, use a **Load Balancer** and don't use a public IP

## EC2 Placement Groups

Sometimes you want control over the EC2 instance placement strategy, that strategy can be defined using **Placement Groups**.

We don't get direct interaction with the hardware of AWS but we let AWS know how we want our EC2 instances to be placed compared to one another.

These are the various strategies for a placement group:

- **Cluster** - clusters instances into a low-latency (but high-risk) group in a single AZ
  <img width="45%" src="https://user-images.githubusercontent.com/48696735/180252321-ff5274f9-a1d7-4c96-bca7-a321e13f1aae.png">

  **Pros:**
  - Great network (10 Gbps bandwidth between instances)
  
  **Cons:**
  - If the rack fails, all instances fail at the same time
  
  **Use cases:**
  - Big Data job that needs to complete fast
  - Application that needs extremely low latency and high network throughput

- **Spread** - spreads instances across different hardware (max 7 instances per group per AZ) - for critical applications
  <img width="60%" src="https://user-images.githubusercontent.com/48696735/180262803-8b6c96c7-bf13-4585-8c32-459fcfc6c04c.png">

  **Pros:**
  - Can span across AZs
  - Reduced risk of simultaneous failure
  - EC2 instances are on different physical hardware
  
  **Cons:**
  - Limited to 7 intances per AZ per placement group
  
  **Use cases:**
  - Application that needs to maximize high availability
  - Critical applications where each instance must be isolated from failure

- **Partition** - similar to **spread** but it spreads instances across different partitions

  <img width="45%" src="https://user-images.githubusercontent.com/48696735/180267029-165d992e-f5b4-47e0-91e4-d59d119529a5.png">

  - Up to 7 partitions per AZ
  - Can span across multiple AZs in the same region
  - Up to 100s of EC2 instances
  - The instances in a partition do not share racks with the instances in ther other partitions
  - A partition failure can affect many EC2 but won't effect other partitions
  - EC2 instances get access to the partition information as metadata
  
  **Use cases:**
  - HDFS, HBase, Cassandra, Kafka

## Elastic Network Interfaces (ENI)

- An _elastic network interface_ is a logical networking component in a VPC that represents a virtual network card
- It can have following attributes:
  - A primary private IPv4 address from the IPv4 address range of your VPC
  - One or more secondary private IPv4 addresses from the IPv4 address range of your VPC
  - One Elastic IP address (IPv4) per private IPv4 address
  - One public IPv4 address
  - One or more IPv6 addresses
  - One or more security groups
  - A MAC address
  - A source/destination check flag
  - A description
- You can create ENI independently and attach them on the fly on EC2 instances for failover
- Bound to a specific AZ

## EC2 Hibernate

TODO

## EC2 Nitro

TODO

## Understanding vCPU

TODO

## Capacity Reservations

TODO
