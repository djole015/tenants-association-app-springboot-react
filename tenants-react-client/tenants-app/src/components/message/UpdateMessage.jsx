import React, { Component } from "react";
import ApiMessageService from "./ApiMessageService.js";
import moment from "moment";
import { Formik, Form, Field } from "formik";

class UpdateMessage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      id: this.props.match.params.id,
      title: "",
      type: "",
      percentageRequired: "",
      description: "",
      createdAt: moment(new Date()).format("YYYY-MM-DD"),
      flatId: "",
    };

    this.onSubmit = this.onSubmit.bind(this);
    this.loadMessage = this.loadMessage.bind(this);
  }

  componentDidMount() {
    this.loadMessage();
    console.log(this.state);
  }

  loadMessage() {
    ApiMessageService.getMessageById(this.state.id).then((response) => {
      let message = response.data;
      this.setState({
        title: message.title,
        type: message.type,
        percentageRequired: response.data.percentageRequired,
        description: message.description,
        createdAt: moment(message.createdAt).format("YYYY-MM-DD"),
        flatId: message.flatId,
      });
    });
  }

  onSubmit(e) {
    let message = {
      id: this.state.id,
      title: e.title,
      type: e.type,
      percentageRequired: e.percentageRequired,
      description: e.description,
      createdAt: e.createdAt,
      flatId: e.flatId,
    };

    /* console.log(message);
    this.props.history.push("/messages");
 */
    ApiMessageService.updateMessage(message)
      .then(() => {
        this.props.history.push("/messages");
      })
      .catch((error) => {
        console.log(error.response);
      });
  }

  render() {
    let {
      id,
      title,
      type,
      percentageRequired,
      description,
      createdAt,
      flatId,
    } = this.state;
    return (
      <div>
        <h1>Message</h1>
        <div className="container">
          <Formik
            initialValues={{
              id,
              title,
              type,
              percentageRequired,
              description,
              createdAt,
              flatId,
            }}
            enableReinitialize={true}
            onSubmit={this.onSubmit}
          >
            {(props) => (
              <Form>
                <fieldset className="form-group">
                  <label>Id</label>
                  <Field
                    type="text"
                    readOnly
                    name="id"
                    className="form-control"
                  />
                </fieldset>
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
                    type="text"
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

export default UpdateMessage;
