window.onload = function() {
    const errorMessage = document.getElementById('errorMessage');
    if(errorMessage && errorMessage.textContent.trim() !== '') {
        alert(errorMessage.textContent.trim());
    }
}