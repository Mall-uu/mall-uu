/*
 * Copyright (c) 2022. Kang PilGyu
 * All rights reserved.
 */

CREATE TABLE IF NOT EXISTS users(
    id Serial NOT NULL,
    password VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    username VARCHAR NULL,
    nickname VARCHAR NULL,
    sns_type VARCHAR NULL,
    sns_id VARCHAR NULL,
    sns_connected_at TIMESTAMP NULL,
    last_login_at TIMESTAMP NULL,
    is_active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMP NULL
)




