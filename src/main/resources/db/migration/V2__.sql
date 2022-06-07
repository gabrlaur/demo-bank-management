INSERT
INTO
  bank_statements
  (id, date_time, beneficiary_id, amount, currency, comment)
VALUES
  (1, NOW(), 1, 12, 'USD', 'this is a first transaction');

  INSERT
INTO
  beneficiaries
  (id, first_name, last_name, account_number)
VALUES
  (1, 'Bob', 'Smith', 'LT123123123');