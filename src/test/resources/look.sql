SELECT
  uuser0_.username AS username1_2_,
  uuser0_.enabled  AS enabled2_2_,
  uuser0_.password AS password3_2_
FROM UUser uuser0_ INNER JOIN UUser_GGroup groups1_ ON uuser0_.username = groups1_.users_username
  INNER JOIN GGroup ggroup2_ ON groups1_.groups_name = ggroup2_.name
WHERE uuser0_.username = ? AND uuser0_.enabled = 1 AND (? IN (SELECT authoritie3_.authorities
                                                              FROM GGroup_authorities authoritie3_
                                                              WHERE ggroup2_.name = authoritie3_.GGroup_name))
LIMIT ?
