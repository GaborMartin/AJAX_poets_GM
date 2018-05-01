function searchSubstring(occurence) {
    const occurenceEl = document.getElementById('occurence');
    occurenceEl.textContent = occurence;
}

function onSearchResponse() {
    clearMessages();
    if (this.status === OK) {
        searchSubstring(JSON.parse(this.responseText));
    } else {
        const errorDivEl = document.getElementById('error');
        onOtherResponse(errorDivEl, this);
    }
}

function onSearchClicked() {
    const seekerForm = document.forms['seeker'];

    const subString = seekerForm.querySelector('input[name="substring"]');
    const subStringValue = subString.value;

    const params = new URLSearchParams();
    params.append('substring', subStringValue);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onSearchResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'protected/work');
    xhr.send(params);
    }

function onWorkLoad(work) {
    const workContentSpanEl = document.getElementById('contentofwork');
    workContentSpanEl.textContent = work.content;
}


function onWorkResponse() {
    if (this.status === OK) {
        showContents(['profile-content', 'works-content','work-content', 'logout-content']);
        onWorkLoad(JSON.parse(this.responseText));
    } else {
        onOtherResponse(workContentDivEl, this);
    }
}