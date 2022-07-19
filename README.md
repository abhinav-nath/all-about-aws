## Table of contents

- [Regions and Availability Zones](#regions-and-availability-zones "Regions and Availability Zones")
  - [How to choose an AWS region?](#how-to-choose-an-aws-region "How to choose an AWS region?")
  - [Availability Zones (AZs)](#availability-zones-azs "Availability Zones (AZs)")

## AWS Budget Setup for Free Tier - Important!!

It is a good idea to create a budget to keep track of any charges while using AWS.

Go to `Billing -> Budgets -> Create Budget` and create a cost budget.

Also setup alerts on reaching threshold costs and specify multiple email ids so that you get prompt notification before it is too late!


## Regions and Availability Zones

Regions are all around the world (ex: us-east-1, eu-west-2 ...)

A region is a physical location around the world and is a cluster of data centers

Each group of logical data centers is called an **Availability Zone**

Most AWS services are **region-scoped**

### How to choose an AWS region?

- **Compliance** with data governance and legal requirements: _data never leaves a region without your explicit permission_
- **Proximity** to customers: _reduced latency_
- **Available services** within a region: _new services and new features aren't available in every region_
- **Pricing** varies from region to region

### Availability Zones (AZs)

An Availability Zone (AZ) is one or more discrete data centers with redundant power, networking, and connectivity in an AWS Region.

Each region has many availability zones (usually 3, min is 2, max is 6).
Example:

- ap-southeast-2a
- ap-southeast-2b
- ap-southeast-2c

<img width="257" alt="image" src="https://user-images.githubusercontent.com/48696735/179051415-44e8f879-345a-4287-9152-a66506d967dc.png">

Each AZ has independent power, cooling, and physical security and is connected via redundant, ultra-low-latency networks.

All AZs in an AWS Region are interconnected with high-bandwidth, low-latency networking, over fully redundant, dedicated metro fiber providing high-throughput, low-latency networking between AZs.

All traffic between AZs is encrypted.

The network performance is sufficient to accomplish synchronous replication between AZs.

AZs make partitioning applications for high availability easy.

If an application is partitioned across AZs, companies are better isolated and protected from issues such as power outages, lightning strikes, tornadoes, earthquakes, and more.

AZs are physically separated by a meaningful distance, many kilometers, from any other AZ, although all are within 100 km (60 miles) of each other.

AWS has Global Services like these:
- IAM
- Route 53 (DNS service)
- Cloudfront (Content Delivery Network)

Region-scoped services:
- Amazon EC2 (IaaS)
- Elastic Beanstalk (PaaS)
- Lambda (Function as a Service)
- Rekognition (SaaS)

Here is a list of all the [Regional Services](https://aws.amazon.com/about-aws/global-infrastructure/regional-product-services/?p=ngi&loc=4 "AWS Regional Services")


## Route 53

### DNS Terminologies

- **Domain Registrar** - Amazon Route 53, GoDaddy, ...
- **DNS Records** - A, AAAA, CNAME, NS, ...
- **Zone File** - contains DNS records
- **Name Server** - resolves DNS queries (Authoritive or Non-Authoritive)
- **Top Level Domain (TLD)** - .com, .us, .in, .gov, .org, ...
- **Second Level Domain (SLD)** - amazon.com, google.com, ...

**Route 53** connects user requests to internet applications running on AWS or on-premises.
The name is a possible reference to U.S. Routes, and "53" is a reference to the **TCP/UDP** port `53`, where DNS server requests are addressed.

Amazon Route 53 supports full, end-to-end DNS resolution over IPv6.


[Go to top ⬆️](#table-of-contents)
