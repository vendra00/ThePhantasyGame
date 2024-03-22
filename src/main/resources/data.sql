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

-- Add more as needed for your game...


INSERT INTO player (name, level, experience, health, mana, stamina, strength, dexterity, constitution, intelligence, wisdom, charisma, race_id)
VALUES ('HeroPlayer', 1, 0, 100, 50, 75, 10, 10, 10, 10, 10, 10, 1); -- Assuming '1' is the ID for a valid race, e.g., Human

