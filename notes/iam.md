# Identity and Access Management (IAM)

- [IAM : Permissions](#iam--permissions "IAM : Permissions")
- [IAM Policies Structure](#iam-policies-structure "IAM Policies Structure")
- [IAM : Password Policy](#iam--password-policy "IAM : Password Policy")
- [Multi Factor Authentication - MFA](#multi-factor-authentication---mfa "Multi Factor Authentication - MFA")
- [How can users access AWS?](#how-can-users-access-aws "How can users access AWS?")
- [Create an Access Key and configure it in AWS CLI](#create-an-access-key-and-configure-it-in-aws-cli "Create an Access Key and configure it in AWS CLI")
- [IAM Roles](#iam-roles "IAM Roles")
- [IAM Security Tools](#iam-security-tools "IAM Security Tools")

IAM provides fine-grained access control across all of AWS.
With IAM, you can specify who can access which services and resources, and under which conditions.

<img width="80%" alt="image" src="https://user-images.githubusercontent.com/48696735/179809454-32d3f974-23bb-4d82-bcf8-96a03cfb6b8d.png">

- IAM is a **Global service**
- **Root account** created by default, shouldn't be used or shared
- Create Users using the Root account
- **Users** are people within your organization, and can be grouped
- **Groups** only contain users, not other groups
- Users don't have to belong to a group, and one user can belong to multiple groups

<img width="50%" alt="image" src="https://user-images.githubusercontent.com/48696735/179809318-803ae010-74ea-4057-9481-f619114769db.png">

## IAM : Permissions

- Users or Groups can be assigned **policies** (as JSON documents)

  <img width="40%" alt="image" src="https://user-images.githubusercontent.com/48696735/179822821-274e1d87-0b06-4d37-9434-393172fdeebb.png">

- These policies define the **permissions** of the users/groups
- In AWS, you apply the **least privilege principle**: don't give more permission than a user needs

## IAM Policies Structure

<img width="441" alt="image" src="https://user-images.githubusercontent.com/48696735/179835213-4bbcdc4d-82fe-43fe-a699-94b78175dcd1.png">

- **Version** : policy language version, always include `2012-10-17`

- **Id** : an identifier for the policy (optional)

- **Statement** : one or more individual statements (required)

  Statements consists of:
  
  - **Sid** : Statement Id (optional)
  
  - **Effect** : whether the statement allows of denies access (`Allow`, `Deny`)
  
  - **Principal** : account/user/role to which this policy will be applied to
  
  - **Action** : list of action this policy allows of denies
  
  - **Resource** : list of resources to which the actions will be applied to
  
  - **Condition** : condition for when this statement will be applied (optional)

## IAM : Password Policy

- Strong passwords = higher security for your account
- In AWS, you can setup a password policy by going to `IAM -> Account Settings -> Create Password Policy`:
  <img width="80%" alt="image" src="https://user-images.githubusercontent.com/48696735/180049001-0372816f-9090-4755-b9da-8c480117858f.png">
- A password policy is really helpful against brute-force attacks on your account
- But, there is a second defense mechanism - **MFA**

## Multi Factor Authentication - MFA

- Users have access to your account and can possibly change configurations or delete resources in your AWS account
- You want to protect your Root account and IAM users
- You can do it via **MFA**
- `MFA = password you know + security device you own`
- Main benefit of MFA:
  - Even if a password is stolen or hacked, the account is not compromised

### MFA device options in AWS

<img width="80%" alt="image" src="https://user-images.githubusercontent.com/48696735/180038934-3ec3f46d-66e7-4de2-ae4c-56434aad731e.png">
<img width="80%" alt="image" src="https://user-images.githubusercontent.com/48696735/180039003-9df65b80-ec78-4dfc-b4ae-b50ee7b2ddd2.png">

## How can users access AWS?

- To access AWS, you have three options:
  1. **AWS Management Console**: protected by password + MFA
  2. **AWS Command Line Interface (CLI)**: protected by access keys
     - Can be used in local machine or using [AWS CloudShell](https://docs.aws.amazon.com/cloudshell/latest/userguide/welcome.html "AWS CloudShell")
  4. **AWS Software Developer Kit (SDK)**: (for code) protected by access keys
- Access Keys are generated through the **AWS Console**
- Users manage their own access keys
- Access Keys are secret, just like a password. Don't share them!
- **Access Key ID ~= username**
- **Secret Access Key ~= password**

## Create an Access Key and configure it in AWS CLI

- Go to `IAM -> Users -> Summary -> Security Credentials -> Create Access Key`
- ```shell
  ~ aws configure
  AWS Access Key ID [None]: HFE8R3R0FFKJHWEFFW3G
  AWS Secret Access Key [None]: afKIJH23e2njnfweFDSjfs7392fafefSDV
  Default region name [None]: ap-south-1
  Default output format [None]:
  ```
- Check the user
  ```shell
  ~ aws iam list-users
  {
    "Users": [
        {
            "Path": "/",
            "UserName": "someuser",
            "UserId": "HFE8R3R0FFKJHWEFFW3G",
            "Arn": "arn:aws:iam::423433242:user/someuser",
            "CreateDate": "2022-07-19T19:19:45+00:00",
            "PasswordLastUsed": "2022-07-20T16:31:55+00:00"
        }
    ]
  }
  ```

## IAM Roles

- An IAM role is an identity you can create that has specific permissions with credentials that are valid for short durations
- Roles can be assumed by entities that you trust
- Roles have:
  1. Trusted entity (like AWS Service, AWS account etc)
  2. Permissions
- Use case:
  - Create a role for EC2 to have a `IAMReadOnlyAccess` permission

## IAM Security Tools

- **IAM Credential Report (account-level)**
  - A report that lists all your account's users and the status of their various credentials
  - `IAM -> Credential report`
 
- **IAM Access Advisor (user-level)**
  - Access advisor shows the service permissions granted to a user and when those services were last accessed
  - You can use this information to revise your policies
  - `Users -> Summary -> Access Advisor`
