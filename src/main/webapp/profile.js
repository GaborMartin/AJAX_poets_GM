
function onWorksLoaded() {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onWorksResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/works');
    xhr.send();
}

function onProfileLoad(poet) {
    clearMessages();
    showContents(['profile-content', 'works-content', 'logout-content']);

    const poetNameSpandEl = document.getElementById('poet-name');
    poetNameSpandEl.textContent = poet.name;
    onWorksLoaded();
}
