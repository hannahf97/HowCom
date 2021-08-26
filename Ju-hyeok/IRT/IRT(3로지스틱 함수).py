import json
import numpy as np
import matplotlib.pyplot as plt
x=np.arange(-3,3.1,0.1)
x=np.round_(x,1)
IRT=[]
with open('C://Users//xpdlw//Desktop//A010000025_A010025001.json')as json_file:
    json_data = json.load(json_file)

    dif_level =json_data['difficultyLevel']
    guess_level = json_data['guessLevel']
    dis_level = json_data['discriminationLevel']

print(type(dif_level))
print(guess_level)
print(dis_level)
print(json_data)
print(x)
for i in x:
    IRT.append(guess_level+(1-guess_level)/(1+(np.exp(1))**(-1.702*dis_level*(i-dif_level))))


plt.plot(x,IRT)
plt.show()

