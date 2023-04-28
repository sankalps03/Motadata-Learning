<!DOCTYPE html>
<html>
<head>
    <title>jQuery Form Example</title>
    <link
            rel="stylesheet"
            href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css"
    />
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="getPost.js"></script>
</head>
<body>
<div class="col-sm-6 col-sm-offset-3">
    <h1>Motadata Allies</h1>

    <form action="" method="POST">
        <div id="name-group" class="form-group">
            <label for="name">Name</label>
            <input
                    type="text"
                    class="form-control"
                    id="name"
                    name="name"
                    placeholder="Full Name"
            />
        </div>

        <div id="email-group" class="form-group">
            <label for="email">Email</label>
            <input
                    type="text"
                    class="form-control"
                    id="email"
                    name="email"
                    placeholder="email@example.com"
            />
        </div>

        <div id="Motadata-group" class="form-group">
            <label for="MotadataAlly">Motadata Ally</label>
            <input
                    type="text"
                    class="form-control"
                    id="MotadataAlly"
                    name="superheroAlias"
                    placeholder="Ant Man, Wonder Woman, Black Panther, Superman, Black Widow"
            />
        </div>

        <button type="submit" class="btn btn-success">
            Submit
        </button>

        <button class="btn btn-success">
            Retrieve
        </button>
    </form>
</div>
</body>
</html>