# EC2 Storage Options

- [Amazon Elastic Block Store](#amazon-elastic-block-store "Amazon Elastic Block Store")
  - [EBS Volume Types](#ebs-volume-types "EBS Volume Types")
  - [EBS Multi-Attach](#ebs-multi-attach "EBS Multi-Attach")
  - [Delete on Termination attribute](#delete-on-termination-attribute "Delete on Termination attribute")
  - [EBS Snapshots](#ebs-snapshots "EBS Snapshots")
    - [EBS Snapshot Archive](#ebs-snapshot-archive "EBS Snapshot Archive")
    - [Recycle Bin for EBS Snapshots](#recycle-bin-for-ebs-snapshots "Recycle Bin for EBS Snapshots")
- [Amazon EC2 Instance Store](#amazon-ec2-instance-store "Amazon EC2 Instance Store")
- [Use Amazon EFS with Amazon EC2](#use-amazon-efs-with-amazon-ec2 "Use Amazon EFS with Amazon EC2")
- [Use Amazon S3 with Amazon EC2](#use-amazon-s3-with-amazon-ec2 "Use Amazon S3 with Amazon EC2")

![image](https://user-images.githubusercontent.com/48696735/180452409-11680f62-b786-4266-8b38-c328303d0f7c.png)

## Amazon Elastic Block Store (EBS) <img align="right" width="8%" src="https://user-images.githubusercontent.com/48696735/180450459-120c4a57-f206-4ad1-bed9-450fc68168a0.png">

- An **EBS (Elastic Block Store) Volume** is a **network drive** you can attach to you instances while they run
  - **Con:** It uses the network to communicate with the instance, so there might be a bit of latency
  - **Pro:** It can be detached from one EC2 instance and attached to another one quickly 
- It allows your instances to persist data, even after their termination
- One EBS can only be mounted to one instance at a time
- They are bound to to a **specific AZ**
  - An EBS volume in us-east-1a cannot be attached to us-east-1b
  - To move a volume across, you first need to snapshot it
- You can use EBS as a primary storage device for data that requires frequent and granular updates
- For example, EBS is the recommended storage option when you run a database on an instance
- EBS volumes can also be created as encrypted volumes using the Amazon EBS encryption feature
- To keep a backup copy of your data, you can create a snapshot of an EBS volume, which is stored in **Amazon S3**
- **Free tier:** 30 GB per month of free EBS storage of type **General Purpose (SSD)** or **Magnetic**

### EBS Volume Types

[Documentation](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-volume-types.html "EBS Volume Types")

1. **Solid State Drives (SSD)**
   1. **General Purpose SSD** - Provides a balance of price and performance
      1. gp2
      2. gp3
   2. **Provisioned IOPS SSD** - Provides high performance for mission-critical, low-latency, or high-throughput workloads
      1. io1
      2. io2
      
      *[EBS Multi-Attach](#ebs-multi-attach "EBS Multi-Attach") is supported in this type*
2. **Hard disk drives (HDD)**
   1. **Throughput Optimized HDD**
      - st1
   2. **Cold HDD**
      - sc1
3. **Previous generation volume types**
   - Magnetic

### EBS Multi-Attach

- Only supported by Provisioned IOPS SSD family (io1 and io2)
- Attach the same EBS volume to multiple EC2 instances in the same AZ
- Each instance has full read & write permissions to the volume
- Use cases:
  - Achieve **higher application availability** in clustered Linux applications (like Teradata)
  - Where applications must be able to manage concurrent write operations
- Must use a file system that's cluster-aware (not XFS, EX4, etc)

### Delete on Termination attribute
<img src="https://user-images.githubusercontent.com/48696735/180496768-9cbe5c0a-f53f-478e-960c-8b3cc286ad4c.png">
- Can be configured for root volume while creating the EC2 instance
- Controls the EBS behavior when an EC2 instance terminates
- By default, the **root** EBS volume is deleted (attribute enabled)
- By default, any **other** EBS volume is not deleted (attribute disabled)

### EBS Snapshots

<img width="60%" src="https://user-images.githubusercontent.com/48696735/180498141-09cd5ba0-a511-4fcd-9c83-1f66bace2849.png">

- Make a backup (snapshot) of your EBS volume at a point in time
- Not necessary to detach volume to do snapshot, but recommended
- Why snapshots?
  - We can copy snapshots across AZ or Region
- [How to take a Snapshot](https://aws.plainenglish.io/aws-article-9-ebs-snapshots-663cee351441 "How to take a Snapshot")

#### EBS Snapshot Archive

<img width="280" src="https://user-images.githubusercontent.com/48696735/180499299-31f3a8f6-0233-4ae2-b175-ba39fd46889f.png">

- Move a Snapshot to an *archive tier* that is **75% cheaper**
- Takes within 24 to 72 hours for restoring the archive

#### Recycle Bin for EBS Snapshots

<img width="280" src="https://user-images.githubusercontent.com/48696735/180499631-6f3eeb3e-e7cb-4dd0-a27d-a57e97682472.png">

- Setup rules to retain deleted snapshots so that you can recover them after an accidental deletion
- Specify retention (from 1 day to 1 year)

## Amazon EC2 Instance Store <img align="right" width="100" src="https://user-images.githubusercontent.com/48696735/180611522-8dca9ee7-3a27-4fab-ae2b-bb3997254ca8.png">

- [Documentation](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/InstanceStorage.html "EC2 Instance Store Documentation")
- EBS volumes are **network drives** with good but "limited" performance
- If you need a high-performance **hardware disk**, use EC2 Instance Store

**Main advantage:**
- Better I/O performance

**Caveat:**
- The data in an instance store persists only during the lifetime of its associated instance (ephemeral[^1])
- If an instance reboots (intentionally or unintentionally), data in the instance store **persists**
- However, data in the instance store is lost under any of the following circumstances:
  - The underlying disk drive fails
  - The instance stops
  - The instance hibernates
  - The instance terminates
- When you stop, hibernate, or terminate an instance, every block of storage in the instance store is reset

Therefore, do not rely on instance store for valuable, long-term data. Instead, use more durable data storage, such as Amazon S3, Amazon EBS, or Amazon EFS.

Instance store is ideal for temporary storage of information that changes frequently, such as
- buffers
- caches
- scratch data, and
- other temporary content
  
or for data that is replicated across a fleet of instances, such as a load-balanced pool of web servers

## Use Amazon EFS with Amazon EC2

## Use Amazon S3 with Amazon EC2

[^1]: temporary, lasting for a very short time
