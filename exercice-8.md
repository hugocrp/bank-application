# Exercice 8

Il y'a effectivement des bugs trouvé dans les fonctions révélé grâce à mes tests.

## withdrawMoney

Bug 1 : La condition utilise withdrawAmount < withdrawLimit au lieu de <=, 
ce qui empêche de retirer exactement la limite du montant qu'il ya dans le compte.

Bug 2 : On peut retirer 0 (le retrait est considéré comme réussi alors qu'il ne fait rien).

Bug 3 : Pas de validation pour les valeurs spéciales (Infinity, NaN) qui peuvent causer des 
comportements imprévisibles.

## depositMoney

Bug 1 : On peut déposer 0...
Bug 2 : il n'y a pas de validation pour les montants spéciaux (Double.MAX_VALUE, Infinity, NaN)
