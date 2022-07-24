# Elastic Load Balancing (ELB)

- [Why use a load balancer?](#why-use-a-load-balancer "Why use a load balancer?")
- [Why use an Elastic Load Balancer?](#why-use-an-elastic-load-balancer "Why use an Elastic Load Balancer?")
- [Health Checks](#health-checks "Health Checks")

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
