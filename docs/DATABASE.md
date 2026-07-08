                 USER
                  │
          1       │      *
                  │
                 ITEM
      ┌──────────┼──────────┐
      │          │          │
      │          │          │
CATEGORY     REMINDER   ATTACHMENT
      │
      │
 ITEM_TAG
      │
      │
     TAG

     # Database Design


# Database Schema

The application consists of the following entities:

- User
- Item
- Category
- Tag
- ItemTag
- Reminder
- Attachment

---

# Entity Details

## User

Stores account information for each registered user.
 - id , name , username , email , password. profile_picture,bio,created_at,updated_at
---

## Item

Represents everything a user saves.

Examples:

- Internship
- Hackathon
- Job Opportunity
- Project Idea
- Article
- YouTube Video
- Notes
- Useful Link

| Field       | Type         | Constraints       |
|-------------|--------------|-------------------|
| id          | BIGINT       | Primary Key       |
| title       | VARCHAR(255) | Not Null          |
| description | TEXT         | Nullable          |
| url         | VARCHAR(1000)| Nullable          |
| priority    | ENUM         | LOW, MEDIUM, HIGH |
| status      | ENUM         | ACTIVE, ARCHIVED  |
| favorite    | BOOLEAN      | Default False     |
| deadline    | DATETIME     | Nullable          |
| created_at  | TIMESTAMP    | Not Null          |
| notes       | TEXT         | Nullable          |
| updated_at  | TIMESTAMP    | Not Null          |
| user_id     | BIGINT       | Foreign Key       |
| category_id | BIGINT       | Foreign Key       |

---

## Category

Used to organize saved items.

Default categories include:

- Internship
- Hackathon
- Project
- Learning
- Article
- Job
- Notes
- Other

| Field| Type   | Constraints |
|------|------  |-------------|
| id   | BIGINT | Primary Key |
| name | VARCHAR(100) | Unique   |
| color| VARCHAR(20)  | Nullable |
| icon | VARCHAR(100) | Nullable |
| created_at | TIMESTAMP | Not Null |

---

## Tag

Tags provide additional classification for saved items.

Examples:

- React
- Java
- Google
- Remote
- Summer
- Open Source

| Field | Type | Constraints |
|------|------|-------------|
| id | BIGINT | Primary Key |
| name | VARCHAR(100) | Unique |

---

## ItemTag

Creates a many-to-many relationship between Items and Tags.

| Field | Type | Constraints |
|------|------|-------------|
| item_id | BIGINT | Foreign Key |
| tag_id | BIGINT | Foreign Key |

Composite Primary Key:

- item_id
- tag_id

---

## Reminder

Stores reminder information for saved items.

| Field | Type | Constraints |
|------|------|-------------|
| id | BIGINT | Primary Key |
| reminder_time | DATETIME | Not Null |
| repeat_type | ENUM | NONE, DAILY, WEEKLY, MONTHLY |
| status | ENUM | ACTIVE, COMPLETED |
| item_id | BIGINT | Foreign Key |

---

## Attachment

Stores files attached to an item.

Supported attachments include:

- Images
- PDFs
- Documents
- Screenshots

| Field | Type | Constraints |
|------|------|-------------|
| id | BIGINT | Primary Key |
| file_name | VARCHAR(255) | Not Null |
| file_url | VARCHAR(500) | Not Null |
| file_type | VARCHAR(50) | Not Null |
| uploaded_at | TIMESTAMP | Not Null |
| item_id | BIGINT | Foreign Key |

---

# Relationships

## User → Item

One user can own multiple items.

Relationship:

One-to-Many

---

## Category → Item

One category can contain multiple items.

Relationship:

One-to-Many

---

## Item → Reminder

One item can have multiple reminders.

Relationship:

One-to-Many

---

## Item → Attachment

One item can have multiple attachments.

Relationship:

One-to-Many

---

## Item ↔ Tag

An item can have multiple tags.

A tag can belong to multiple items.

Relationship:

Many-to-Many

---



# Database Constraints

- Email must be unique.
- Username must be unique.
- Every Item must belong to a User.
- Every Item must belong to a Category.
- Deleting a User removes all associated Items.
- Deleting an Item removes its Reminders and Attachments.
- Duplicate Item-Tag combinations are not allowed.

---

# Indexing Strategy

Indexes will be created on:

- email
- username
- title
- deadline
- category_id
- user_id
- favorite

These indexes improve search performance and dashboard queries.

---

# Future Enhancements

The schema is designed to support future features without major changes.

Possible additions include:

- AI generated summaries
- OCR extracted text
- Shared collections
- Team workspaces
- Public profiles
- Browser extension
- Activity logs
- Notification history
- File versioning

---

# Design Principles

The database is designed to be:

- Normalized
- Scalable
- Maintainable
- Easy to query
- Flexible for future features

