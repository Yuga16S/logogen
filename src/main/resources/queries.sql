-- Model Changes --
 CREATE TABLE logos (
  id SERIAL PRIMARY KEY,
  company_name VARCHAR(500),
  domain VARCHAR(100),
  url VARCHAR(1000),
  approved BOOLEAN DEFAULT FALSE
 );

-- Data Changes --
INSERT INTO logos (company_name, domain, url) VALUES ('Instacart', 'instacart.ca', 'https://www.instacart.com/assets/beetstrap/brand/2022/instacart-logo-color-6678cb82d531f8910d5ba270a11a7e9b56fc261371bda42ea7a5abeff3492e1c.svg');