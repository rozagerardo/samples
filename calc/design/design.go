package design

import (
	. "goa.design/goa/v3/dsl"
)

// Object ...
var Object = Type("object", func() {
	Description("Object")
	Attribute("id", String, "A string to identify the Object.", func() {
		MinLength(1)
		Meta("struct:tag:bson", "_id")
		Meta("struct:tag:json", "id")
	})

	// Mandatory Attributes
	Attribute("key", String, "Object's Key", func() {
	})
	Attribute("name", String, "Object's Name", func() {
	})
	Attribute("logo", String, "Object's Logo", func() {
	})
	Attribute("enabled", Boolean, "Object is enabled or not", func() {
		Default(true)
	})

	// Optional Attributes
	Attribute("createdDate", String, "Date when the Object was created default: now.", func() {
		Format(FormatDateTime)
	})
	Attribute("updatedDate", String, "Date when the Object was updated default: now.", func() {
		Format(FormatDateTime)
	})

	Required("id", "key", "name")
})

// ObjectMedia defines the media type used to render object.
var ObjectMedia = ResultType("application/vnd.object.media+json", func() {
	Description("A Object")
	Reference(Object)

	// Attributes define the media type shape.
	Attributes(func() {
		Attribute("id", String, "Unique object ID")
		Attribute("key", String, "Key of Object")
		Attribute("name", String, "Name of Object")
		Attribute("logo", String, "Logo of Object")
		Attribute("enabled", Boolean, "Object is enabled or not")

		Attribute("createdDate", String, "Date when the Object was created default: now.", func() {
			Format(FormatDateTime)
		})
		Attribute("updatedDate", String, "Date when the Object was updated default: now.", func() {
			Format(FormatDateTime)
		})

		Required("id", "key", "name", "enabled")
	})

	// View defines a rendering of the media type.
	// Media types may have multiple views and must have a "default" view
	View("default", func() {
		Attribute("id")
		Attribute("key")
		Attribute("name")
		Attribute("logo")
		Attribute("enabled")
		Attribute("createdDate")
		Attribute("updatedDate")
	})

	// View defines a rendering of the media type.
	// Media types may have multiple views and must have a "default" view
	View("full", func() {
		Attribute("id")
		Attribute("key")
		Attribute("name")
		Attribute("logo")
		Attribute("enabled")
		Attribute("createdDate")
		Attribute("updatedDate")
	})
})

// ObjectPost ...
var ObjectPost = Type("ObjectPost", func() {
	Reference(Object)
	Description("Object Post")
	Attribute("id")
	Attribute("key")
	Attribute("name")
	Attribute("logo")
	Attribute("enabled")

	Required("name", "key")
})

var _ = Service("objects", func() {
	HTTP(func() {
		Path("/objects")
	})

	Method("show", func() {
		Description("Get object by id")
		HTTP(func() {
			GET("/{objectID}")
			Response("NotFound", StatusNotFound)
			Response("BadRequest", StatusBadRequest)
		})
		Payload(func() {
			Attribute("objectID", String, "Object ID")
		})

		Result(ObjectMedia, func() {
            // IF I REMOVE THIS VIEW USAGE, THE REDECLARED TYPES ERROR DISSAPEAR
			View("full")
		})
		Error("NotFound")
		Error("BadRequest")
	})
    
    Method("create", func() {
		Description("Insert a new object")
		HTTP(func() {
			POST("/")
			Response("InternalServerError", StatusInternalServerError)
		})
		Payload(ObjectPost)

		Result(ObjectMedia)
		Error("InternalServerError")
	})
})

// ObjectList is a list of objects
var ObjectList = ResultType("application/vnd.object.list.media+json", func() {
	Description("A list of Objects")

	Attributes(func() {
		Attribute("total", Int, "Total objects found.", func() {})
		Attribute("page", Int, "Page.", func() {})
		Attribute("limit", Int, "Limit.", func() {})
		Attribute("offset", Int, "Offset.", func() {})
		Attribute("search", String, "Search.", func() {})
		Attribute("sort", String, "Sort.", func() {})

		Attribute("data", CollectionOf(ObjectMedia), "A list of objects id and url.")
	})

	View("default", func() {
		Attribute("total")
		Attribute("page")
		Attribute("limit")
		Attribute("offset")
		Attribute("search")
		Attribute("sort")
		Attribute("data", func() {
			View("default")
		})

	})
})