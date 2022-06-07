CREATE TABLE bank_statements (
  id BIGINT NOT NULL,
   date_time datetime NOT NULL,
   beneficiary_id BIGINT NOT NULL,
   comment VARCHAR(255) NULL,
   amount DECIMAL NOT NULL,
   currency VARCHAR(255) NOT NULL,
   CONSTRAINT pk_bank_statements PRIMARY KEY (id)
);

CREATE TABLE beneficiaries (
  id BIGINT NOT NULL,
   first_name VARCHAR(255) NOT NULL,
   last_name VARCHAR(255) NOT NULL,
   account_number VARCHAR(255) NOT NULL,
   CONSTRAINT pk_beneficiaries PRIMARY KEY (id)
);