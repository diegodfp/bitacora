package com.diavlo.bitacora.notifications.domain.entity;


import java.sql.Timestamp;

import com.diavlo.bitacora.users.domain.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "message_text", columnDefinition = "text")
    private String messageText;

    @Column(name = "notification_type", columnDefinition = "varchar(50)")
    private String notificationType;

    @Column(name = "related_entity_type", columnDefinition = "varchar(50)")
    private String relatedEntityType;

    @Column(name = "related_entity_id")
    private Long relatedEntityId;

    @Column(name = "is_read")
    private Boolean isRead;

    @Column(name = "sent_at")
    private Timestamp sentAt;

}
