ALTER TABLE cards ADD COLUMN bank_name varchar(25) NOT NULL;
ALTER TABLE cards ADD COLUMN province varchar(25) NOT NULL;
ALTER TABLE cards ADD COLUMN city varchar(25) NOT NULL;
ALTER TABLE cards DROP COLUMN "open_address";
ALTER TABLE cards ADD COLUMN bankOutlet varchar(50) NOT NULL;
