# Amazon EC2 (Elastic Compute Cloud) <img align="right" width="8%" alt="image" src="https://user-images.githubusercontent.com/48696735/180069707-f7b3f370-0228-4f6e-8e14-62af470dac7d.png">

- [Overview](#overview "Overview")
- [EC2 sizing & configuration options](#ec2-sizing--configuration-options "EC2 sizing & configuration options")
- [EC2 User Data Script](#ec2-user-data-script "EC2 User Data Script")
- [EC2 Instance Types](#ec2-instance-types "EC2 Instance Types")
- [EC2 Security Groups](#ec2-security-groups "EC2 Security Groups")

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

