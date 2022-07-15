## Table of contents

- [Regions and Availability Zones](#regions-and-availability-zones "Regions and Availability Zones")
  - [How to choose an AWS region?](#how-to-choose-an-aws-region "How to choose an AWS region?")
  - [Availability Zones (AZs)](#availability-zones-azs "Availability Zones (AZs)")

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


[Go to top ⬆️](#table-of-contents)
