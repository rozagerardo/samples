package objectsapi

import (
	objects "calc/gen/objects"
	"context"
	"log"
)

// objects service example implementation.
// The example methods log the requests and return zero values.
type objectssrvc struct {
	logger *log.Logger
}

// NewObjects returns the objects service implementation.
func NewObjects(logger *log.Logger) objects.Service {
	return &objectssrvc{logger}
}

// Get object by id
func (s *objectssrvc) Show(ctx context.Context, p *objects.ShowPayload) (res *objects.ObjectMedia, err error) {
	res = &objects.ObjectMedia{}
	s.logger.Print("objects.show")
	return
}
