# Identity and Access Management (IAM)

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

  <img width="50%" alt="image" src="https://user-images.githubusercontent.com/48696735/179822821-274e1d87-0b06-4d37-9434-393172fdeebb.png">

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
