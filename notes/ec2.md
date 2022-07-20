# Amazon EC2 (Elastic Compute Cloud) <img align="right" width="8%" alt="image" src="https://user-images.githubusercontent.com/48696735/180069707-f7b3f370-0228-4f6e-8e14-62af470dac7d.png">

- [Overview](#overview "Overview")
- [EC2 sizing & configuration options](#ec2-sizing--configuration-options "EC2 sizing & configuration options")
- [EC2 User Data Script](#ec2-user-data-script "EC2 User Data Script")
- [EC2 Instance Types](#ec2-instance-types "EC2 Instance Types")

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
- This script is **run only once** when the instance starts
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
