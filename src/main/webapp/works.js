let worksTableEl;
let worksTableBodyEl;


function onWorkClicked() {
    const workId = this.dataset.workId;

    const params = new URLSearchParams();
    params.append('id', workId);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onWorkResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/work?' + params.toString());
    xhr.send();

}

function appendWork(work) {
    const idTdEl = document.createElement('td');
    idTdEl.textContent = work.id;

    const titleaEl = document.createElement('a');
    titleaEl.textContent = work.title;
    titleaEl.href = 'javascript:void(0);';
    titleaEl.dataset.workId = work.id;
    titleaEl.addEventListener('click', onWorkClicked);

    const titleTdEl = document.createElement('td');
    titleTdEl.appendChild(titleaEl);


    const dateTdEl = document.createElement('td');
    dateTdEl.textContent = work.publishedDate;


    const trEl = document.createElement('tr');
    trEl.appendChild(idTdEl);
    trEl.appendChild(titleTdEl);
    trEl.appendChild(dateTdEl);

    worksTableBodyEl.appendChild(trEl);
}

function appendWorks(works) {
    removeAllChildren(worksTableBodyEl);

    for (let i = 0; i < works.length; i++) {
        const work = works[i];
        appendWork(work);
    }
}

function onWorksLoad(works) {
    worksTableEl = document.getElementById('works');
    worksTableBodyEl = worksTableEl.querySelector('tbody');
    appendWorks(works);
}

function onWorksResponse() {
    if (this.status === OK) {
        showContents(['profile-content', 'works-content', 'logout-content']);
        onWorksLoad(JSON.parse(this.responseText));
    } else {
        onOtherResponse(worksContentDivEl, this);
    }
}