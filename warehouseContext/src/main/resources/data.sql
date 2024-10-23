INSERT INTO warehouses (warehouse_number, owner_id, material_type)
VALUES (
           'W-01',
           UNHEX(REPLACE('e53a47fa-9f1b-4d58-8981-cdd01beac39e', '-', '')), -- Converts UUID to binary for owner_id
           'GYPSUM' -- Assumes this is a string
       );