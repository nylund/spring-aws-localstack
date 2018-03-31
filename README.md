
Simple example showing how to combine Spring Boot 2.0, Spring Cloud AWS and Localstack for local development using
Amazon SQS & SNS APIs.

Startup localstack:
```
git clone https://github.com/localstack/localstack
TMPDIR=/private$TMPDIR docker-compose up
```

Setup topics, queues and subscriptions:
```
aws --endpoint-url=http://localhost:4576 sqs create-queue --queue-name test-queue
aws --endpoint-url=http://localhost:4576 sqs create-queue --queue-name test-queue-sns
aws --endpoint-url=http://localhost:4575 sns create-topic --name test-topic
aws --endpoint-url=http://localhost:4575 sns subscribe --topic-arn arn:aws:sns:eu-west-1:123456789012:test-topic --protocol sqs --notification-endpoint arn:aws:sqs:eu-west-1:queue:test-queue-sns
```

References:
- https://github.com/localstack/localstack
- https://github.com/smartupio/localstack-spring-boot
- https://cloud.spring.io/spring-cloud-aws/
