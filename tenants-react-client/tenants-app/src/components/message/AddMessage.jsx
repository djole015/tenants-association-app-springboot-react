import React, { Component } from "react";
import moment from "moment";
import ApiMessageService from "../../services/ApiMessageService.js";
import { Formik, Form, Field } from "formik";

class AddMessage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      title: "",
      type: "",
      percentageRequired: "",
      createdAt: moment(new Date()).format("YYYY-MM-DD"),
      flatId: "",
    };
    this.saveMessage = this.saveMessage.bind(this);
  }

  saveMessage(message) {
    console.log(this.state);
    ApiMessageService.addMessage(message)
      .then((res) => {
        this.props.history.push("/messages");
      })
      .catch((error) => {
        console.log(error.response);
      });
  }
  render() {
    let {
      title,
      type,
      percentageRequired,
      description,
      createdAt,
      flatId,
    } = this.state;
    return (
      <div>
        <h1>New Message</h1>
        <div className="container">
          <Formik
            initialValues={{
              title,
              type,
              percentageRequired,
              description,
              createdAt,
              flatId,
            }}
            enableReinitialize={true}
            onSubmit={this.saveMessage}
          >
            {(props) => (
              <Form>
                <fieldset className="form-group">
                  <label>Title</label>
                  <Field type="text" name="title" className="form-control" />
                </fieldset>
                <fieldset className="form-group">
                  <label>Type</label>
                  <Field type="text" name="type" className="form-control" />
                </fieldset>
                <fieldset className="form-group">
                  <label>Percentage Required</label>
                  <Field
                    type="text"
                    name="percentageRequired"
                    className="form-control"
                  />
                </fieldset>
                <fieldset className="form-group">
                  <label>Description</label>
                  <Field
                    type="text"
                    name="description"
                    className="form-control"
                  />
                </fieldset>
                <fieldset className="form-group">
                  <label>Created At</label>
                  <Field
                    type="date"
                    name="createdAt"
                    className="form-control"
                  />
                </fieldset>

                <fieldset className="form-group">
                  <label>Flat Id</label>
                  <Field type="text" name="flatId" className="form-control" />
                </fieldset>

                <button type="submit" className="btn btn-success">
                  Save
                </button>
              </Form>
            )}
          </Formik>
        </div>
      </div>
    );
  }
}

export default AddMessage;
