INSERT INTO sellers (id, seller_name)
VALUES (UNHEX(REPLACE('e53a47fa-9f1b-4d58-8981-cdd01beac39e', '-', '')), 'BestGypsum.LLC');


INSERT INTO warehouses (warehouse_number, seller_id, material_type)
VALUES ('W-01', UNHEX(REPLACE('e53a47fa-9f1b-4d58-8981-cdd01beac39e', '-', '')), 'GYPSUM' ),
       ('W-02', UNHEX(REPLACE('e53a47fa-9f1b-4d58-8981-cdd01beac39e', '-', '')), 'IRON_ORE'),
       ('W-03', UNHEX(REPLACE('e53a47fa-9f1b-4d58-8981-cdd01beac39e', '-', '')), 'CEMENT');