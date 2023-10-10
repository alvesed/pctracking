
resource "aws_security_group" "pctracking_sg" {
  name        = "pctracking_sg"
  description = "pctracking security group"
  vpc_id      = aws_vpc.pctracking_vpc_1.id

  tags = {
    "Name" = "pctracking_sg"
  }
}

resource "aws_security_group_rule" "public_out" {
  type        = "egress"
  from_port   = 0
  to_port     = 0
  protocol    = "-1"
  cidr_blocks = ["0.0.0.0/0"]

  security_group_id = aws_security_group.pctracking_sg.id
}

resource "aws_security_group_rule" "public_in_ssh" {
  type              = "ingress"
  from_port         = 22
  to_port           = 22
  protocol          = "tcp"
  cidr_blocks       = ["0.0.0.0/0"]
  security_group_id = aws_security_group.pctracking_sg.id
}

resource "aws_key_pair" "pctracking_key" {
  key_name   = "pctracking_key1"
  public_key = file("~/.ssh/pctracking_key1.pub")
}

resource "aws_security_group_rule" "public_in_http" {
  type              = "ingress"
  from_port         = 80
  to_port           = 80
  protocol          = "tcp"
  cidr_blocks       = ["0.0.0.0/0"]
  security_group_id = aws_security_group.pctracking_sg.id
}