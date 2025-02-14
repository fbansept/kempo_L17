
INSERT INTO droit(nom) VALUES
 ("administrateur"),
 ("gestionnaire"),
 ("competiteur");

-- mot de passe : root
INSERT INTO utilisateur(email, password, droit_id) VALUES
("a@a.com", "$2a$10$eVzoMhlgO37Fivn1ywe69OMS51jkahd/39D8XIGWkEqIOFG3as7b.", 1),
("b@b.com", "$2a$10$eVzoMhlgO37Fivn1ywe69OMS51jkahd/39D8XIGWkEqIOFG3as7b.", 2),
("c@c.com", "$2a$10$eVzoMhlgO37Fivn1ywe69OMS51jkahd/39D8XIGWkEqIOFG3as7b.", 3),
("d@d.com", "$2a$10$eVzoMhlgO37Fivn1ywe69OMS51jkahd/39D8XIGWkEqIOFG3as7b.", 3),
("e@e.com", "$2a$10$eVzoMhlgO37Fivn1ywe69OMS51jkahd/39D8XIGWkEqIOFG3as7b.", 3);

INSERT INTO inscrit(id, date_de_naissance, poids, homme, ceinture) VALUES
(3, "2000-06-24",78, 1, "JAUNE"),
(4, "2002-03-18",67, 0, "ORANGE"),
(5, "1998-08-10",90, NULL, "NOIR");
