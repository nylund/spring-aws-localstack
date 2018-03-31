package com.example.service.sqs;

import com.example.service.domain.EventMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

/**
 *
 * Message sent to SNS/SQS are serialized by default serialized using Spring's JSON message converter.
 * However when if the SQS queue source is a SNS topics the payload is not the same as what was sent to SNS.
 * The payload when listening to the queue is a AWS specific JSON format, which wraps the original message.
 *
 * See: https://docs.aws.amazon.com/sns/latest/dg/json-formats.html#http-notification-json
 *
 * For SNS to SQS fanout use cases this must be used:
 *
 * @SqsListener("${sqs.queue}")
 * public void subscribeToSNSFanout(@NotificationMessage EventMessage msg) {... }
 *
 * for services that publish and subscribe directly SQS this style must be used:
 * @SqsListener("${sqs.queue}")
 * public void subscribeToSQSDirectly(EventMessage msg) { .. }
 *
 * The message POJO must also be annotated with either Jackson or Lombok annotations.
 *
 * Jackson:
 *
 * @JsonCreator
 * public EventMessage(@JsonProperty("foo") String foo) {
 *   this.foo = foo;
 * }
 *
 * Lombok:
 * @Data
 * @Builder
 * @NoArgsConstructor
 * @AllArgsConstructor
 * public class EventMessage {
 *   private String foo;
 * }
 */
@Component
@Slf4j
public class SqsSubscriber {

    @SqsListener("${sqs.queue.sns.fanout}")
    public void subscribeToSNSFanout(@NotificationMessage final EventMessage msg) {
        log.error("Received message from SNS topic: " + msg);
    }

    @SqsListener("${sqs.queue}")
    public void subscribeToSQSDirectly(final EventMessage msg) {
        log.error("Received message: " + msg);
    }

}
