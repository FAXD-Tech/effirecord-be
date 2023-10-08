ALTER TABLE cards DROP COLUMN bankOutlet;
ALTER TABLE cards ADD COLUMN bank_outlet varchar(50) NOT NULL;
