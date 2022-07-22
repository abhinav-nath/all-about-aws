# EC2 Storage Options

- [Amazon Elastic Block Store](#amazon-elastic-block-store "Amazon Elastic Block Store")
- [Amazon EC2 instance store](#amazon-ec2-instance-store "Amazon EC2 instance store")
- [Use Amazon EFS with Amazon EC2](#use-amazon-efs-with-amazon-ec2 "Use Amazon EFS with Amazon EC2")
- [Use Amazon S3 with Amazon EC2](#use-amazon-s3-with-amazon-ec2 "Use Amazon S3 with Amazon EC2")

![image](https://user-images.githubusercontent.com/48696735/180452409-11680f62-b786-4266-8b38-c328303d0f7c.png)

## Amazon Elastic Block Store (EBS) <img align="right" width="8%" src="https://user-images.githubusercontent.com/48696735/180450459-120c4a57-f206-4ad1-bed9-450fc68168a0.png">

- An **EBS (Elastic Block Store) Volume** is a **network drive** you can attach to you instances while they run
- It allows your instances to persist data, even after their termination
- One EBS can only be mounted to one instance at a time
- They are bound to to a **specific AZ**
- You can use EBS as a primary storage device for data that requires frequent and granular updates
- For example, EBS is the recommended storage option when you run a database on an instance
- EBS volumes can also be created as encrypted volumes using the Amazon EBS encryption feature
- To keep a backup copy of your data, you can create a snapshot of an EBS volume, which is stored in **Amazon S3**

## Amazon EC2 instance store

## Use Amazon EFS with Amazon EC2

## Use Amazon S3 with Amazon EC2
