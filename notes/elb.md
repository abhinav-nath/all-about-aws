# Elastic Load Balancing (ELB)

- [Why use a load balancer?](#why-use-a-load-balancer "Why use a load balancer?")
- [Why use an Elastic Load Balancer?](#why-use-an-elastic-load-balancer "Why use an Elastic Load Balancer?")
- [Health Checks](#health-checks "Health Checks")
- [Types of Load Balancers on AWS](#types-of-load-balancers-on-aws "Types of Load Balancers on AWS")
- [Load Balancer Security Groups](#load-balancer-security-groups "Load Balancer Security Groups")

<img width="600" src="https://user-images.githubusercontent.com/48696735/180640721-0320b296-643c-413e-82bb-3d3a1d53fe13.png">

## Why use a load balancer?

- Spread load across multiple downstream instances
- Expose a single point of access (DNS) to your application
- Seamlessly handle failures of downstream instances
- Do regular health checks of your instances
- Provide SSL termination (HTTPS) for your websites
- Enforce stickiness with cookies
- High availability across zones
- Separate public traffic from private traffic

## Why use an Elastic Load Balancer?

- ELB is a managed load balancer
  - AWS guarantees that it will be working
  - AWS takes care of upgrades, maintenance, high availability
  - AWS provides only a few configuration options
- It costs less to setup your own load balancer but it will be a lot more effort on your end
- It is integrated with many AWS offerings/services like
  - EC2, EC2 Auto Scaling Groups, Amazon ECS
  - AWS Certificate Manager (ACM), CloudWatch
  - Route 53, AWS WAF, AWS Global Accelerator

## Health Checks

<img width="500" src="https://user-images.githubusercontent.com/48696735/180645678-4467fe89-8974-428e-9cf6-ab22f05d9c59.png">

- Health checks are crucial for Load Balancers
- They enable the load balancer to know if instances it forwards the traffic to are available to respond to requests
- Health Check is done on a port and a route (`/health` is common)
- If the response is not 200 (OK) then the instance is unhealthy

## Types of Load Balancers on AWS

AWS has 4 kinds of managed load balancers:

1. **Classic Load Balancer (CLB)** : v1 - old generation - 2009
   - supports HTTP, HTTPS, TCP, SSL
2. **Application Load Balancer (ALB)** : v2 - new generation - 2016
   - supports HTTP, HTTPS, WebSocket
3. **Network Load Balancer (NLB)** : v2 - new generation - 2017
   - supports TCP, TLS, UDP
4. **Gateway Load Balancer (GWLB)** : 2020
   - operates at layer 3 (network layer) - IP protocol

## Load Balancer Security Groups

TODO
