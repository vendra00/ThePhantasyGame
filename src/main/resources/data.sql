INSERT INTO role (id, name) VALUES
                                (1, 'ROLE_USER'),
                                (2, 'ROLE_ADMIN');



INSERT INTO user (username, password) VALUES
                                          ('admin', '$2a$10$DdXgfCCMTVaLWVVWgiTDguG/ddRN9LOIi16h7dMX9MWn16MbFbY0e'),
                                          ('user', '$2a$10$YOD0S.cKoqrqO2dnc41aiO5ud1eTBjuRNAJvmrZv5rjMAyBranNsq');

INSERT INTO user_roles (user_id, role_id) VALUES
    (1, 2),
    (2, 1);

INSERT INTO race (
                  id,
                  name,
                  description,
                  strength_modifier,
                  dexterity_modifier,
                  constitution_modifier,
                  intelligence_modifier,
                  wisdom_modifier,
                  charisma_modifier)
VALUES (2, 'Elf', 'Elves are known for their grace and mastery of magic and weapons such as the bow and sword.', -1, 3, -2, 2, 1, 1);
INSERT INTO race (id,
                  name,
                  description,
                  strength_modifier,
                  dexterity_modifier,
                  constitution_modifier,
                  intelligence_modifier,
                  wisdom_modifier,
                  charisma_modifier)
VALUES (3, 'Dwarf', 'Stout and powerful, dwarves are known for their skill in warfare, their ability to withstand physical and magical punishment, and their hard work.', 1, 1, 3, -2, 1, -1);
INSERT INTO race (id,
                  name,
                  description,
                  strength_modifier,
                  dexterity_modifier,
                  constitution_modifier,
                  intelligence_modifier,
                  wisdom_modifier,
                  charisma_modifier)
VALUES (1, 'Human', 'Humans are the most adaptable and ambitious people among the common races. They have varied tastes, morals, and customs.', 1, 1, 1, 1, 1, 1);
INSERT INTO race (id,
                  name,
                  description,
                  strength_modifier,
                  dexterity_modifier,
                  constitution_modifier,
                  intelligence_modifier,
                  wisdom_modifier,
                  charisma_modifier)
VALUES (4, 'Orc', 'Orcs are known for their incredible strength and toughness. Generally seen as fearsome warriors, Orcs also have a strong sense of loyalty to their tribe.', 2, 0, 2, -1, -1, -2);
-- Add more as needed for your game...

INSERT INTO race_perks (race_id, name, description, is_positive) VALUES (3, 'Night Vision', 'Can see in the dark.', 1);
INSERT INTO race_perks (race_id, name, description, is_positive) VALUES (2, 'Swift', 'Moves 10% faster.', 1);
INSERT INTO race_perks (race_id, name, description, is_positive) VALUES (1, 'Magic Resistant', '10% resistance to magic.', 1);
INSERT INTO race_perks (race_id, name, description, is_positive) VALUES (4, 'Battle Hardened', '5% resistance to physical damage.', 1);


