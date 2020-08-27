import React, { Component } from "react";
import ApiMessageService from "../../services/ApiMessageService.js";
import { Formik, Form, Field } from "formik";
import moment from "moment";

class MessageDashboard extends Component {
  constructor(props) {
    super(props);

    this.state = {
      messages: [],
      checked: false,
      searchTitle: "",
      searchType: "",
      searchFlatId: "",
      mess: "",
    };

    this.refreshMessages = this.refreshMessages.bind(this);
    this.deleteMessageClicked = this.deleteMessageClicked.bind(this);
    this.updateMessageClicked = this.updateMessageClicked.bind(this);
    this.onChange = this.onChange.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  componentDidMount() {
    this.refreshMessages();
  }

  refreshMessages() {
    let config = { params: {} };

    if (this.state.title !== "") {
      config.params.title = this.state.searchTitle;
    }
    if (this.state.type !== "") {
      config.params.type = this.state.searchType;
    }
    if (this.state.flatId !== "") {
      config.params.flatId = this.state.searchFlatId;
    }
    ApiMessageService.getAllMessages(config).then((response) => {
      this.setState({ messages: response.data });
    });
  }

  handleChange() {
    this.setState({
      checked: !this.state.checked,
    });
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  deleteMessageClicked(id) {
    //console.log(id);
    ApiMessageService.deleteMessage(id).then((response) => {
      this.setState({ mess: `message with id = ${id} deleted` });
      this.refreshMessages();
    });
  }

  addMessageClicked() {
    // redirecting
    this.props.history.push("/newMessageForm");
  }

  updateMessageClicked(id) {
    // redirecting
    console.log(id);
    this.props.history.push(`/messages/${id}`);
  }

  render() {
    let { searchTitle, searchType, searchFlatId } = this.state;
    let hidden = this.state.checked ? "" : "hidden";
    return (
      <div>
        <div className="searchform">
          <label>Show Search Form</label>{" "}
          <input
            type="checkbox"
            checked={this.state.checked}
            onChange={this.handleChange}
          />
        </div>

        <div className={hidden}>
          <div className="container">
            <Formik
              initialValues={{
                searchTitle,
                searchType,
                searchFlatId,
              }}
              onSubmit={this.refreshMessages}
            >
              {(props) => (
                <Form>
                  <fieldset className="form-group">
                    <label>Title</label>
                    <Field
                      type="text"
                      name="searchTitle"
                      className="form-control"
                      onChange={this.onChange}
                    />
                  </fieldset>
                  <fieldset className="form-group">
                    <label>Type</label>
                    <Field
                      type="text"
                      name="searchType"
                      className="form-control"
                      onChange={this.onChange}
                    />
                  </fieldset>
                  <fieldset className="form-group">
                    <label>Flat Id</label>
                    <Field
                      type="text"
                      name="searchFlatId"
                      className="form-control"
                      onChange={this.onChange}
                    />
                  </fieldset>

                  <button type="submit" className="btn btn-info float-left">
                    Search
                  </button>
                </Form>
              )}
            </Formik>
          </div>
        </div>
        <br />
        <br />
        <div className="container">
          <div>
            <h2>Messages</h2>
            <table className="table table-bordered table-striped">
              <thead className="thead-dark">
                <tr>
                  <th>Id</th>
                  <th>Title</th>
                  <th>Type</th>
                  <th>Description</th>
                  <th>Percentage Required</th>
                  <th>Created At</th>
                  <th>Flat Address</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {this.state.messages.map((message) => (
                  <tr key={message.id}>
                    <td>{message.id}</td>
                    <td>{message.title}</td>
                    <td>{message.type}</td>
                    <td>{message.description}</td>
                    <td>{message.percentageRequired}</td>
                    <td>{moment(message.createdAt).format("YYYY-MM-DD")}</td>
                    <td>{message.flatAddress}</td>
                    <td>
                      <button
                        className="btn btn-warning"
                        onClick={() => this.updateMessageClicked(message.id)}
                      >
                        Edit
                      </button>{" "}
                      <button
                        className="btn btn-danger"
                        onClick={() => this.deleteMessageClicked(message.id)}
                      >
                        Delete
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
            <div>
              <button
                className="btn btn-primary"
                onClick={() => this.addMessageClicked()}
              >
                Create Message
              </button>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default MessageDashboard;
