function findBooks() {
    let searchLine = document.getElementById("searchLine").value;
    let searchMethod = document.querySelector("input[name='searchMethod']:checked").value;
    let currentPage = document.getElementById("currentPage").value;
    let numberOfBooksOnPage = document.getElementById("numberOfBooksOnPage").value;
    let genresIds = document.getElementsByName("genresIds");
    let genresIdArray = [];
    for (let index = 0; index < genresIds.length; index++) {
        if (genresIds[index].checked) {
            genresIdArray.push(genresIds[index].value);
        }
    }

    let searcher = {
        searchLine: searchLine,
        searchMethod: searchMethod,
        currentPage: currentPage,
        numberOfBooksOnPage: numberOfBooksOnPage,
        genresIds: genresIdArray,
    };

    fetch("/main", {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "post",
        body: JSON.stringify(searcher)
    })
        .then(response => {
            return response.json();
        })
        .then(json => {
            setCountFoundBooksField(json);

            let divBooks = document.getElementById("divBooks");
            let divBookTemplate = document.createElement("div");
            fillTemplateDivBook(divBookTemplate, json);
            cleanElement(divBooks);
            fillDivBooksFoundBooks(json, divBookTemplate, divBooks);

            let selectPage = document.getElementById("currentPage");
            cleanElement(selectPage);
            fillSelectPage(json, selectPage);
        });
}

function setCountFoundBooksField(json) {
    document.getElementById("countFoundBooks").innerText = json.books.totalElements;
}

function cleanElement(element) {
    while (element.hasChildNodes()) {
        element.removeChild(element.firstChild);
    }
}

function fillTemplateDivBook(divBook, json) {
    console.log(json.bookFields);
    let bookName = document.createElement("p");
    bookName.setAttribute("id", "bookName");
    let bookImgHref = document.createElement("a");
    bookImgHref.setAttribute("id", "bookHref");
    let bookImg = document.createElement("img");
    bookImg.setAttribute("id", "bookImg");
    bookImg.setAttribute("alt", "bookImg");
    let bookMarkField = document.createElement("span");
    bookMarkField.innerText = json.bookFields.bookMark;
    let bookMarkValue = document.createElement("span");
    bookMarkValue.setAttribute("id", "bookAvg");
    let bookGenreField = document.createElement("span");
    bookGenreField.innerText = json.bookFields.bookGenre;
    let bookGenreValue = document.createElement("span");
    bookGenreValue.setAttribute("id", "bookGenre");
    let bookNumberCopiesField = document.createElement("span");
    bookNumberCopiesField.innerText = json.bookFields.numberCopiesBook;
    let bookNumberCopiesValue = document.createElement("span");
    bookNumberCopiesValue.setAttribute("id", "bookCopies");
    let bookAuthorField = document.createElement("span");
    bookAuthorField.innerText = json.bookFields.bookAuthor;
    let bookAuthorFieldValue = document.createElement("span");
    bookAuthorFieldValue.setAttribute("id", "bookAuthor");

    bookImgHref.appendChild(bookImg);
    divBook.append(bookName, bookImgHref, document.createElement("br"), bookMarkField, bookMarkValue,
        document.createElement("br"), bookGenreField, bookGenreValue, document.createElement("br"),
        bookNumberCopiesField, bookNumberCopiesValue, document.createElement("br"), bookAuthorField, bookAuthorFieldValue);
}

function fillDivBooksFoundBooks(json, divBookTemplate, divBooks) {
    json.books.content.forEach(function (book) {
        let cloneNode = divBookTemplate.cloneNode(true);
        $(cloneNode).find("#bookName").text(book.name);
        $(cloneNode).find("#bookHref").attr("href", "/bookInfo?bookId=" + book.id);
        $(cloneNode).find("#bookImg").attr("src", book.image);
        $(cloneNode).find("#bookAvg").text(book.avgMark);
        $(cloneNode).find("#bookGenre").text(book.genre);
        $(cloneNode).find("#bookCopies").text(book.numberCopies);
        $(cloneNode).find("#bookAuthor").text(book.author);

        divBooks.appendChild(cloneNode);
    });
}

function fillSelectPage(json, selectPage) {
    let totalPages = json.books.totalPages;
    if (totalPages > 0) {
        for (let i = 1; i <= json.books.totalPages; i++) {
            let optionElement = document.createElement("option");
            optionElement.innerText = i;
            selectPage.appendChild(optionElement);
        }
        $(selectPage).val(json.books.pageable.pageNumber + 1);
    } else {
        let optionElement = document.createElement("option");
        optionElement.innerText = 1;
        selectPage.appendChild(optionElement);
    }
}