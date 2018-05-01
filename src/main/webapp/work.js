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