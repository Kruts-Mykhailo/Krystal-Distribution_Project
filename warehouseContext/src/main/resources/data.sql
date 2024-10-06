INSERT INTO warehouses (warehouse_id, warehouse_number, owner_id, material_type)
VALUES (
           UNHEX(REPLACE('c56a4180-65aa-42ec-a945-5fd21dec0538', '-', '')), -- Converts UUID to binary
           1, -- Assumes this is an INT type for warehouse number
           UNHEX(REPLACE('e53a47fa-9f1b-4d58-8981-cdd01beac39e', '-', '')), -- Converts UUID to binary for owner_id
           'GYPSUM' -- Assumes this is a string
       );