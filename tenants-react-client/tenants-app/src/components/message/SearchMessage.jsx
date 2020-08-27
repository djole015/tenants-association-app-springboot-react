import React, { Component } from "react";
import ApiMessageService from "../../services/ApiMessageService.js";
import { Formik, Form, Field } from "formik";

class SearchMessage extends Component {
  constructor(props) {
    super(props);

    this.state = {
      messages: [],
      title: null,
      type: null,
      flatId: null,
    };

    this.onChange = this.onChange.bind(this);
    this.searchMessage = this.searchMessage.bind(this);
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  searchMessage() {
    let config = { params: {} };

    if (this.state.title !== null) {
      config.params.title = this.state.title;
    }
    if (this.state.type !== null) {
      config.params.type = this.state.type;
    }
    if (this.state.flatId !== null) {
      config.params.flatId = this.state.flatId;
    }

    ApiMessageService.getAllMessages(config)
      .then((res) => {
        this.setState({ messages: res.data });
        console.log(this.state.messages);
        this.props.history.push("/messages");
      })
      .catch((error) => {
        console.log(error.response);
      });
  }

  render() {
    let { title, type, flatId } = this.state;
    return (
      <div>
        <h1>Search Message</h1>
        <div className="container">
          <Formik
            initialValues={{
              title,
              type,
              flatId,
            }}
            enableReinitialize={true}
            onSubmit={this.searchMessage}
          >
            {(props) => (
              <Form>
                <fieldset className="form-group">
                  <label>Title</label>
                  <Field
                    type="text"
                    name="title"
                    className="form-control"
                    onChange={this.onChange}
                  />
                </fieldset>
                <fieldset className="form-group">
                  <label>Type</label>
                  <Field
                    type="text"
                    name="type"
                    className="form-control"
                    onChange={this.onChange}
                  />
                </fieldset>
                <fieldset className="form-group">
                  <label>Flat Id</label>
                  <Field
                    type="text"
                    name="flatId"
                    className="form-control"
                    onChange={this.onChange}
                  />
                </fieldset>

                <button type="submit" className="btn btn-success">
                  Search
                </button>
              </Form>
            )}
          </Formik>
        </div>
      </div>
    );
  }
}

export default SearchMessage;
