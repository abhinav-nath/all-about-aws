# Amazon Machine Image (AMI) <img align="right" width="100" src="https://user-images.githubusercontent.com/48696735/180500978-579c9086-bf80-42c1-8aa1-cabf95359eb3.png">

> An AMI is a template that contains the software configuration (operating system, application server, and applications) required to launch your instance.

AMI is a customization of an EC2 instance
- You add your own software, configuration, OS, monitoring etc
- Faster boot / configuration time because all your software is pre-packaged

AMIs are built for a **specific region** (and can be copied across regions)

You can launch EC2 instances from:
- **A public AMI**: AWS provided, Ex - Amazon Linux
- **You own AMI**: you make and maintain them yourself
- **An AWS Marketplace AMI**: an AMI someone else made (and potentially sells)

## AMI lifecycle

![image](https://user-images.githubusercontent.com/48696735/180501375-1c8d1456-4f0e-42f1-9534-2f37efb5ce8a.png)

- After you create and register an AMI, you can use it to launch new instances (you can also launch instances from an AMI if the AMI owner grants you launch permissions)
- You can copy an AMI within the same AWS Region or to different AWS Regions
- When you no longer require an AMI, you can deregister it

## Create AMI from an EC2 instance

<img width="500" alt="image" src="https://user-images.githubusercontent.com/48696735/180502464-c86e5c01-0bd6-4846-9620-a2881002983b.png">

- Start an EC2 instance and customize it
- Stop the instance (for data integrity)
- Build an AMI - this will also create EBS Snapshots
- Launch instances from other AMIs
- [Reference](https://docs.aws.amazon.com/toolkit-for-visual-studio/latest/user-guide/tkv-create-ami-from-instance.html)
