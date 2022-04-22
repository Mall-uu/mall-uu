/*
 * Copyright (c) 2022. Kang PilGyu
 * All rights reserved.
 */

CREATE TABLE IF NOT EXISTS registration_email_tokens(
    id Serial NOT NULL,
    email_token VARCHAR NOT NULL,
    user_id INTEGER NOT NULL,
    expired_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
)




