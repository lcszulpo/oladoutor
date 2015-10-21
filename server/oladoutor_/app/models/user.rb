class User < ActiveRecord::Base

  def self.create(name, email, password)
    user = User.new
    user.name = name
    user.email = email
    user.password = password

    if user.save
      true
    else
      false
    end
  end

  def self.update(id, name, email, password)
    user = User.find(id)
    user.name = name
    user.email = email
    user.password = password

    if user.save
      true
    else
      false
    end
  end

  def self.show
    User.all
  end

  def self.find_by_email_password(email, password)
    User.where(email: email).where(password: password)
  end

end
