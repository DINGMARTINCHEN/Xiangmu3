import pandas as pd
from sklearn.linear_model import LinearRegression
import statsmodels.api as sm
import joblib
import os

DATA_PATH = 'Data/IT行业收入表.xlsx'
MODEL_PATH = 'Saved_Models'
os.makedirs(MODEL_PATH, exist_ok=True)
MODEL_FILE = os.path.join(MODEL_PATH, 'IT行业_model.pkl')

def train_salary_model():
    df = pd.read_excel(DATA_PATH)
    print('IT行业数据预览:')
    print(df.head())

    x = df[['工龄']]
    y = df['薪水']

    model = LinearRegression()
    model.fit(x,y)

    x_with_const = sm.add_constant(x)
    statsmodels_model = sm.OLS(y,x_with_const).fit()

    joblib.dump(model, MODEL_FILE)
    print('模型已保存')

    print('\nIT行业模型参数:')
    print(f'斜率:{model.coef_[0]:.4f}')
    print(f'截距:{model.intercept_:.4f}')
    print('\n统计模型摘要')
    print(statsmodels_model.summary())
    return model,df,x,y

def predict_salary(workingAge):
  model = joblib.load(MODEL_FILE)
  prediction_data = pd.DataFrame({'工龄':[workingAge]})
  prediction = model.predict(prediction_data)
  return prediction[0]


def main():
    # train_salary_model()
    print(predict_salary(1))

if __name__ == '__main__':
     main()
