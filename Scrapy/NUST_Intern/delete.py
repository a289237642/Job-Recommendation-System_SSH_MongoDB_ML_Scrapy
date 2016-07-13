import os
f = open('NUST_detailtest_YYT.json','r+')
n = open('NUST_detailtest_YYT_new.json','w')
for line in f.readlines():
    line = line[:-2]+'\n'
    print line 
    n.truncate()
    n.write(line)
f.close()
    