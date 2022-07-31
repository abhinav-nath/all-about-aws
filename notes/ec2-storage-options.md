# EC2 Storage Options

- [Amazon Elastic Block Store (EBS)](#amazon-elastic-block-store-ebs "Amazon Elastic Block Store (EBS)")
  - [EBS Volume Types](#ebs-volume-types "EBS Volume Types")
  - [EBS Multi-Attach](#ebs-multi-attach "EBS Multi-Attach")
  - [Delete on Termination attribute](#delete-on-termination-attribute "Delete on Termination attribute")
  - [EBS Snapshots](#ebs-snapshots "EBS Snapshots")
    - [EBS Snapshot Archive](#ebs-snapshot-archive "EBS Snapshot Archive")
    - [Recycle Bin for EBS Snapshots](#recycle-bin-for-ebs-snapshots "Recycle Bin for EBS Snapshots")
  - [EBS Encryption](#ebs-encryption "EBS Encryption")
- [Amazon EC2 Instance Store](#amazon-ec2-instance-store "Amazon EC2 Instance Store")
- [Amazon Elastic File System (EFS)](#amazon-elastic-file-system-efs "Amazon Elastic File System (EFS)")
- [Use Amazon S3 with Amazon EC2](#use-amazon-s3-with-amazon-ec2 "Use Amazon S3 with Amazon EC2")

<img width="550" src="https://user-images.githubusercontent.com/48696735/180452409-11680f62-b786-4266-8b38-c328303d0f7c.png">

## Amazon Elastic Block Store (EBS)<img align="right" width="8%" src="https://user-images.githubusercontent.com/48696735/180450459-120c4a57-f206-4ad1-bed9-450fc68168a0.png">

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

---

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

📌 When creating EC2 instances, you can only use the following EBS volume types as boot volumes: gp2, gp3, io1, io2, and Magnetic (Standard).

---

### EBS Multi-Attach

- Only supported by Provisioned IOPS SSD family (io1 and io2)
- Attach the same EBS volume to multiple EC2 instances in the **same AZ** 📌
- Each instance has full read & write permissions to the volume
- Use cases:
  - Achieve **higher application availability** in clustered Linux applications (like Teradata)
  - Where applications must be able to manage concurrent write operations
- Must use a file system that's cluster-aware (not XFS, EX4, etc)

---

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

---

### EBS Encryption

- When you create an encrypted EBS volume, you get the following:
  - Data at rest is encrypted inside the volume
  - All the in-flight data moving between the instance and the volume is encrypted
  - All snapshots are encrypted
  - All volumes created from the snapshot are encrypted
- Encryption has a minimal impact on latency
- EBS encryption leverages keys from KMS (AES-256)
- Copying an unencrypted snapshot allows encryption
- Snapshots of encrypted volumes are also encrypted

#### Encrypt an unencrypted EBS volume

- Create an EBS snapshot of the volume
- Encrypt the snapshot (using copy)
- Create new EBS volume from the encrypted snapshot (the new volume will also be encrypted)
- Now you can attach the encrypted volume to the original EC2 instance

## Amazon EC2 Instance Store<img align="right" width="100" src="https://user-images.githubusercontent.com/48696735/180611522-8dca9ee7-3a27-4fab-ae2b-bb3997254ca8.png">

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

## Amazon Elastic File System (EFS)<img align="right" width="80" src="https://user-images.githubusercontent.com/48696735/180619398-b13a1ab1-e26b-49a6-ae6d-083cfc37e980.png">

[Documentation](https://docs.aws.amazon.com/efs/latest/ug/whatisefs.html "EFS Documentation")

Amazon EFS provides a simple, scalable, fully managed elastic NFS[^2] for use with AWS Cloud services and on-premises resources.

Amazon EFS is designed to provide massively parallel shared access to thousands of Amazon EC2 instances, enabling your applications to achieve high levels of aggregate throughput and IOPS with consistent low latencies.

Amazon EFS supports the Network File System version 4 (NFSv4.1 and NFSv4.0) protocol.

- EFS works with EC2 instances in multi-AZ
- Highly available, scalable, expensize (3 x gp2), pay per use
- To control access to EFS, you need to setup a security group
- Compatible with Linux based AMIs (not Windows)
- Encryption at rest using KMS
- File system scales automatically, pay-per-use, no capacity planning needed in advance
- Use cases:
  - Content management
  - Web serving
  - Data sharing
  - Wordpress

### EFS - Performance & Storage Classes

**EFS Scale**
- 1000s of concurrent NFS clients, 10 GB+ /s throughput
- Grows to Petabyte scale NFS, automatically

**Performance mode (set at EFS creation time)**
- General purpose (default): latency-sensitive use cases (web server, CMS, etc)
- Max I/O: higher latency, throughput, highly parallel (big data, media processing)

**Throughput mode**
- **Bursting** mode (default): 1 TB = 50 MiB/s + burst of up to 100 MiB/s
- **Provisioned** mode: set your throughput regardless of storage size, ex: 1 GiB/s for 1 TB storage

[Storage Classes](https://docs.aws.amazon.com/efs/latest/ug/storage-classes.html "EFS Storage Classes")

<img width="900" src="https://user-images.githubusercontent.com/48696735/180619164-4d3dd469-79dd-4bae-bf94-06cf0b7188f4.png">

- **Storage Tiers** (lifecycle management feature - move file to a different tier after N days)
  - **Standard:** for frequently accessed files
  - **Infrequent Access (EFS-IA):** cost to retrieve files, lower price to store. Enable EFS-IA with a Lifecycle Policy.

- **Availability and Durability**
  - **Standard:** Multi-AZ, great for prod
  - **One Zone:** One AZ, great for dev, backup enabled by default, compatible with IA (**EFS One Zone-IA**)

*We can leverage EFS-IA for great cost savings*

<img width="300" src="https://user-images.githubusercontent.com/48696735/180619111-ff83e95b-80a6-4348-a251-5e5f65f97850.png">

## Use Amazon S3 with Amazon EC2

TODO

[^1]: ephemeral - temporary, lasting for a very short time
[^2]: NFS - Network File System
