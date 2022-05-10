WITH RECURSIVE ancestor(m,d) as (SELECT mom_id AS m, dad_id AS d FROM pedigree WHERE puppy_id=11
                                 UNION ALL
                                 SELECT mom_id, dad_id FROM ancestor, pedigree
                                 WHERE ancestor.m=pedigree.puppy_id OR ancestor.d=pedigree.puppy_id)
SELECT * FROM ancestor;
