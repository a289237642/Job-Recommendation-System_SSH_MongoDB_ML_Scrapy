import os
f = open('NJUPT_detailtest_YYT.json','r+')
n = open('NJUPT_detailtest_YYT_new.json','w')
for line in f.readlines():
    line = line[:-2]+'\n'
    print line 
    n.truncate()
    n.write(line)
f.close()
    
