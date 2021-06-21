### PIXEL PIPELINE
- how much data did this pipeline process?
- how much money did this project save?
- what were the short term tradeoffs made?
    - get project done ASAP b/c money - what functionality was left out?
- changes to make cookies compliant

### PARTNER PIPELINE
- what's the difference between our databases?
    - athena, cassandra, dynamodb
    - pricing
    - read/write capabilities/limits
- how do we ensure idempotence?
    - what stops multiple instances of a service from processing the same data?
    - what are the source/tracker files roles in this?
- how much data did this pipeline process?

## Summary

At Survata, I worked on the infrastructure to obtain, normalize, and store ad data. This was done in two ways: advertisement endpoints and sharing ad data between partner companies.

For the ad endpoints, I helped set up the endpoints that would be called when someone viewed an ad. This involved managing cookies correctly for tracking, anonymous tracking, opting out. The endpoint gets 8 billion month hits with daily peaks of around 10000 requests per second. The first task I tackled was moving the code over from AWS Lambda and API Gateway over to Kubernetes on EKS. We did this to save costs, both the current costs and also scaling costs. Lambda charges per endpoint hit in increments of 100ms, but most of the requests were 10ms or less, so we were already paying 10x more than needed. Additionally, API Gateway ate up huge costs. If Survata ever wanted to increase their traffic by adding their endpoints to more ads, then the costs would scale linearly with that, which wasn't good. We looked towards Kubernetes on EKS because it was much cheaper and can scale up instances as needed. It charges by the hour so it scales better with growth. Survata saved 90% of costs in the first month, which was between 10-30 thousand.

Secondly, before we could move everything over, there were no existing tests of any kind. I had to write everything--unit, integration, stress, and baseline tests. After we moved the code over, I helped set up load balancers to manage the traffic, added metrics to track for endpoint and cluster health, created dashboards to visualize everything.

For sharing partner data, this was done through a data pipeline that used messaged based microservices. It processed 40-50 billion monthly rows of data. The whole process looked roughly like this: a partner would drop their ad data into an S3 bucket, the pipeline would pick it up, process and normalize the data, then store it to several data stores based on the type of data (impressions/segments). Originally, the data pipeline did all of this in one go, but that had some problems. It took longer, if a file ever got messed up during the process, the whole thing had to be redone. It was inefficient if Survata wanted to scale the data, and the backlog of files to process grew bigger and bigger. So we broke the process up into 3 parallelized microservices. The first was called Dispatch. Its job was to realize when a new file was placed, it would read the high level information, create all the necessary tracker files, then send messages to the next step to continue. The second step was called Process. Its job was to read the partner data file, process all of the rows of data in batches, normalize the data, then save the normalized data to Survata's S3 buckets. Once again, once it finished, it would send a message to the next step, which is Store. Store would read the normalized data from the S3 buckets and save the data to various data stores based on the type of data (impressions/segments).

Personally, I was involved in creating a flexible system to handle new partners. Partners send their data in all kinds of formats and we needed a robust system to handle all different kinds of cases. I also worked on creating batched writes to the data stores to improve efficiency. A lot of the code was untested so I had to work on that. Various bugs such as getting duplicate messages for data files we've already stored. Idempotency to make sure repeating things only got added once.

DynamoDB - pricing by read/write

## Partner Pipeline

**summary**
data pipeline that uses messaged based microservices.
takes in partner data, processes/canonicalizes it, saves it to various data stores. handles 40-50 billion rows of data monthly.

- parallelization - dispatch/process/store. sqs messages with database trackers.
- idempotency
- data stores
    - athena, cassandra, dynamo
    - batched writes, tracker updates
- handles many different formats of data

problems
- helped rewrite a monolithic, tightly-coupled, convoluted, untested data pipeline
    - tightly coupled - files had to be processed all at once
    - solution
        - write tests for all the code
- duplicate sqs messages
    - not functionally broken, but adds extra clutter

**resume writeup**
Infrastructure
-

## Pixel Pipeline

**summary**
handle incoming cookie requests. pixel on ad -> send request to lambda. avg request took 1-2ms, but lambda charges by 100ms increments. expensive and scales linearly. 8 billion requests per month, about 3000 per second. peak of 10000 per second.

moved to a kubernetes cluster. unit tests, integration tests, stress tests, baselines. recorded metrics on traffic, kubernetes health, created a visual dashboard (grafana). load balancer in kubernetes. hosted on EKS.

costs:
API gateway, lambda. first month after changes yielded a 90% cost reduction.

**resume writeup**
- Worked on updating software that handles pixel endpoints. Processed roughly 8 billion requests a month, with an average of 3,000 requests per second and a daily peak of roughly 10,000 requests per second.
- New endpoint hosting showed a 90% cost reduction, where the initial cost was in the tens of thousands.
- Worked on unit, integration, stress, baseline testing.
- Recorded and visualized metrics on traffic, Kubernetes health.
- Worked on setting up Kubernetes, load balancers, EKS.

Relevant Technologies: Java, Node.js, Jest, Artillery, Kubernetes, Grafana, Prometheus,

# Survata Databases

![amazon-dynamo-db.png](:storage/954428f8-3be2-4bd0-945e-880ebff106ed/5001c95d.png)![1_tVAwnrBEp6q1CW7P9nbeRg.png](:storage/954428f8-3be2-4bd0-945e-880ebff106ed/cc92e3a9.png)

### DynamoDB & Cassandra
- Very fast primary key lookup.
  - Good for checking if someone has seen an ad.
- Not good for making general queries.
  - E.g. "How many people saw this ad yesterday?"

### DynamoDB vs. Cassandra
Pixels have timestamps associated with it. This can be used to track several things, such as when a user viewed an ad or how many times a user has viewed the ad. However, the data we receive from partners isn't like that. There are no timestamps--it's either you're part of this group or not (e.g. Were you at a baseball game?)
- With Cassandra, we pay for storage space, not amount of write/read requests, which is good because we write/read a lot without necessarily adding new rows.
  - Updating last time viewing ad, number of times viewed.
  - Reading data for analytics.
- With Dynamo, we pay for read/writes.

![download.png](:storage/954428f8-3be2-4bd0-945e-880ebff106ed/7cada1aa.png)

### Athena
- Really good at making aggregation, making complicated queries.
- Really bad at primary key lookups because it would look through every single row.
- Technically, not a database, but a Query Service for S3.

![download (1).png](:storage/954428f8-3be2-4bd0-945e-880ebff106ed/f2b17b83.png)

### Simple Storage Solution (S3)
- Fast data/object storage.
- Can't append, can only read/write.

### Firehose
- Middleware that helps with batching for S3.
  - It would be inefficient to constantly write to S3 every time there's an update. Instead, we write to S3 in huge batches periodically.
  - Used for our real-time pipeline, not partners. Partners write directly to S3, we don't have to cover that cost.


## How Do Our Partners Send Data To Us?
- They have our cookies for users.
- They'll push a ton of data to our S3, usually. They'll use the credentials we provide.
- When S3 gets a new file, it'll push a message to a queue, which we read. When we see the message, we'll pull data from S3.

## COOKIES
https://web.dev/samesite-cookies-explained/
https://www.chromium.org/updates/same-site

samesite, secure

interactions with our existing cookies

## Kubernetes
https://kubernetes.io/docs/tasks/run-application/horizontal-pod-autoscale/
https://www.nginx.com/blog/nginx-plus-ingress-controller-kubernetes-load-balancing/
