function validateFloatOrNull(input) {
  const value = input.value.trim();

  if(value === ''){
    input.setCustomValidity('');
    return;
  }
  const floatRegex = /^[+-]?(\d+\.?\d*|\.\d+)([eE][+-]?\d+)?$/;

 if (!floatRegex.test(value)){
    input.setCustomValidity('请输入有效的浮点数');
  }else{
   input.setCustomValidity('');
  }
}

function validateFloatAndNull(input) {
  const value = input.value.trim();

  if(value === ''){
    input.setCustomValidity('该输入框不能为空');
    return;
  }
  const floatRegex = /^[+-]?(\d+\.?\d*|\.\d+)([eE][+-]?\d+)?$/;

 if (!floatRegex.test(value)){
    input.setCustomValidity('请输入有效的浮点数');
  }else{
   input.setCustomValidity('');
  }
}
