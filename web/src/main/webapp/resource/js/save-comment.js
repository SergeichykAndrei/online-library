function saveComment(bookId, username) {
    let comment = document.getElementById("comment").value;

    let reqBody = {
        comment: comment,
        bookId: bookId,
        username: username
    };

    fetch("/bookInfo", {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "post",
        body: JSON.stringify(reqBody)
    })
        .then(response => {
            return response.json();
        })
        .then(json => {
            let divComments = document.getElementById('comments');
            let divComment = document.getElementById('comment');
            $(divComments).empty();

            json.forEach(function (json) {
                let span = document.createElement("span");
                span.innerText = json.accountName + ':' + json.value;
                divComments.append(span, document.createElement("br"));
            });

            $(divComment).empty();
        });
}