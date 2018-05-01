let worksTableEl;
let worksTableBodyEl;

function appendWork(work) {
    const idTdEl = document.createElement('td');
    idTdEl.textContent = work.id;

    const titleTdEl = document.createElement('td');
    titleTdEl.textContent = work.title;

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