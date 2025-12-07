# 正确版：salaryLinearRegression.py
import pickle
import numpy as np
from flask import Flask, request, jsonify

# 初始化Flask应用
app = Flask(__name__)

# 全局模型变量（先初始化为None，避免启动时崩溃）
model = None

# 加载模型（封装成函数，增加异常处理）
def load_salary_model():
    global model
    try:
        # 模型文件路径：确保和脚本同目录，若不存在则提示
        model_path = "IT行业_model.pkl"
        with open(model_path, "rb") as f:
            model = pickle.load(f)
        print(f"✅ 模型加载成功：{model_path}")
    except FileNotFoundError:
        print(f"❌ 模型文件不存在：{model_path}，将返回测试值")
        model = None  # 模型加载失败时置空
    except Exception as e:
        print(f"❌ 模型加载失败：{str(e)}")
        model = None

# 启动时加载模型
load_salary_model()

# 预测接口（POST方法，接收JSON参数）
@app.route("/SalaryLinearRegressionPredict", methods=["POST"])
def predict_salary():
    # 步骤1：接收并校验参数
    try:
        # 接收Java传递的JSON参数（必须用request.json）
        data = request.get_json()
        if not data or "workingAge" not in data:
            return jsonify({"error": "参数缺失，需要workingAge"}), 400

        # 转换为浮点型（防止类型错误）
        workingAge = float(data["workingAge"])
        print(f"📥 接收参数：工龄={workingAge}")

        # 步骤2：模型预测（模型不存在则返回测试值）
        if model is not None:
            # 关键：scikit-learn预测需要二维数组（[[工龄]]）
            X = np.array([[workingAge]])
            predict_result = model.predict(X)[0]
            # 保留2位小数，避免数值过长
            predict_salary = round(float(predict_result), 2)
        else:
            # 模型缺失时的兜底测试值
            predict_salary = round(5000 + workingAge * 1000, 2)

        # 步骤3：返回JSON结果（Java端要解析salary字段）
        return jsonify({"salary": predict_salary})

    except ValueError:
        return jsonify({"error": "workingAge必须是数字"}), 400
    except Exception as e:
        return jsonify({"error": f"预测失败：{str(e)}"}), 500

# 启动服务（关键：host=0.0.0.0 允许外部访问，debug=True方便调试）
if __name__ == "__main__":
    # host=0.0.0.0：解决仅本地127.0.0.1可访问的问题
    # port=5000：和Java端调用的端口保持一致
    # debug=True：启动时显示错误日志，方便排查
    app.run(host="0.0.0.0", port=5000, debug=True)